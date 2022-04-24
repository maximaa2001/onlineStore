package by.bsuir.service.impl;

import by.bsuir.dao.ProductDao;
import by.bsuir.dao.ProductImageDao;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductImage;
import by.bsuir.entity.dto.file.FileLinkListDto;
import by.bsuir.service.FileService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private ProductDao productDao;
    private ProductImageDao productImageDao;

    private Cloudinary cloudinary;
    private Map<String, String> values;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Autowired
    public FileServiceImpl(ProductDao productDao, ProductImageDao productImageDao){
        this.productDao = productDao;
        this.productImageDao = productImageDao;
    }

    @PostConstruct
    public void init(){
        values = new HashMap<>();
        values.put("cloud_name", cloudName);
        values.put("api_key", apiKey);
        values.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(values);
    }


    @Transactional
    @Override
    public FileLinkListDto upload(List<MultipartFile> files, Integer productId) throws IOException {
        log.info("size {}", files.size());
        Product product = productDao.findById(productId);
        productImageDao.removeByProduct(product);
        List<ProductImage> images = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i).getName());
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(files.get(i).getBytes());
            fo.close();
            Map upload = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            images.add(defaultImage(product, (String)upload.get("secure_url")));
        }
        productImageDao.saveAll(images);
        return FileLinkListDto.of(images.stream().map(ProductImage::getImageUrl).collect(Collectors.toList()));
    }

    private ProductImage defaultImage(Product product, String url){
       return ProductImage.builder()
                .imageUrl(url)
                .product(product)
                .build();
    }
}
