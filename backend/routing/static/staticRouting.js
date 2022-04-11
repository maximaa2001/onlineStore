const staticController = require('../../controller/staticController')

const allCategoriesEndpoint = "/api/categories/all"

const routing = (app) =>{
    app.route(allCategoriesEndpoint).get(staticController.getCategories)
}


module.exports.routing = routing;