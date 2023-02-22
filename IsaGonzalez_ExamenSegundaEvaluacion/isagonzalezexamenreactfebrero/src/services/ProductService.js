import axios from "axios";

const PRODUCTS_API_BASE_URL = "http://localhost:8080/api/v1/products"

class ProductService {

    getAllProducts() {
        return axios.get(PRODUCTS_API_BASE_URL);
    }

    getProductById(productId) {
        return axios.get(PRODUCTS_API_BASE_URL + '?id=' + productId);
    }

    createNewProduct(product) {
        return axios.post(PRODUCTS_API_BASE_URL, product);
    }

    updateProduct(productId, product) {
        return axios.put(PRODUCTS_API_BASE_URL + '/' + productId, product);
    }

    deleteProduct(productId) {
        return axios.delete(PRODUCTS_API_BASE_URL + '/' + productId);
    }
}

export default new ProductService();