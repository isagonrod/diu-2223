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
            <Link to="/add-product" className="btn">
                <img
                    src="https://img.icons8.com/color/600/000000/add-product.png"
                    alt="ADD PRODUCT"/>
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
                            <tr key={product.id}>
                                <td>{product.id}</td>
                                <td>{product.name}</td>
                                <td>{product.stock}</td>
                                <td>{product.price}</td>
                                <td>
                                    <Link className="btn" to={`/edit-product/${product.id}`}>
                                        <img
                                            src="https://www.graphicsfuel.com/wp-content/uploads/2012/07/pencil-icon-512.png"
                                            alt="EDIT"/>
                                    </Link>
                                    <button className="btn" onClick={() => deleteProduct(product.id)}>
                                        <img
                                            src="https://cdn-icons-png.flaticon.com/512/3221/3221845.png"
                                            alt="DELETE" />
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