import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import ProductService from "../services/ProductService";

import 'bootstrap/dist/css/bootstrap.min.css';

const ListProducts = () => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        getAllProducts();
    }, []);

    const getAllProducts = () => {
        ProductService.getAllProducts().then((response) => {
            setProducts(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        });
    }

    const deleteProduct = (productId) => {
        ProductService.deleteProduct(productId).then(() => {
            getAllProducts();
        }).catch(error => {
            console.log(error);
        });
    }

    return (
        <div className="container">
            <h2 className="text-center">PRODUCTS</h2>
            <Link to="/add-product" className="btn btn-primary mb-2">
                {/*ADD PRODUCT*/}
                <img src="src/images/add-product.png" />
            </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>STOCK</th>
                    <th>PRICE</th>
                    <th>ACTIONS</th>
                </thead>
                <tbody>
                {
                    products.map(
                        product =>
                            <tr key = {product.id}>
                                <td>{product.id}</td>
                                <td>{product.name}</td>
                                <td>{product.stock}</td>
                                <td>{product.price}</td>
                                <td>
                                    <Link className="btn btn-info" to={`/edit-product/${product.id}`}>
                                        {/*EDIT*/}
                                        <img src="src/images/edit-product.png"/>
                                    </Link>
                                    <button className="btn btn-danger m1-2" onClick={() => deleteProduct(product.id)}>
                                        {/*DELETE*/}
                                        <img src="src/images/delete-product.png"/>
                                    </button>
                                </td>
                            </tr>
                    )
                }
                </tbody>
            </table>
        </div>
    )
}

export default ListProducts;