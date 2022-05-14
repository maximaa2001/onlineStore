const staticController = require('../../controller/staticController')

const allCategoriesEndpoint = "/api/categories/all"

const allCitiesEndpoint = "/api/cities/all"


const routing = (app) =>{
    app.route(allCategoriesEndpoint).get(staticController.getCategories)
    app.route(allCitiesEndpoint).get(staticController.getCities)
}


module.exports.routing = routing;