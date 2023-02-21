import React, {useEffect, useState} from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import ProductService from "../services/ProductService";

import 'bootstrap/dist/css/bootstrap.min.css';

const AddProduct = () => {

    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [stock, setStock] = useState('');
    const [price, setPrice] = useState('');
    const navigate = useNavigate();
    const {productId} = useParams();

    const saveOrUpdateProduct = (e) => {
        e.preventDefault();

        const product = {id, name, stock, price};

        if (productId) {
            ProductService.updateProduct(productId, product).then(() => {
                navigate('/products');
            }).catch(error => {
                console.log(error);
            });
        } else {
            ProductService.createNewProduct(product).then((response) => {
                console.log(response.data);
                navigate('/products');
            }).catch(error => {
                console.log(error);
            });
        }
    }

    useEffect(() => {
        ProductService.getProductById(productId).then((response) => {
            setId(response.data.id)
            setName(response.data.name)
            setStock(response.data.stock)
            setPrice(response.data.price)
        }).catch(error => {
            console.log(error);
        });
    }, []);

    const title = () => {
        if (productId) {
            return <h2 className="text-center">EDIT PRODUCT</h2>
        } else {
            return <h2 className="text-center">ADD PRODUCT</h2>
        }
    }

    return (
        <div>
            <br/><br/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3">
                        {title()}
                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <label className="form-label">ID:</label>
                                    <input type="text" placeholder="Enter product ID" name="id" className="form-control" value={id} onChange={(e) => setId(e.target.value)}/>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">NAME:</label>
                                    <input type="text" placeholder="Enter product name" name="name" className="form-control" value={name} onChange={(e) => setName(e.target.value)}/>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">STOCK:</label>
                                    <input type="text" placeholder="Enter product stock" name="stock" className="form-control" value={stock} onChange={(e) => setStock(e.target.value)}/>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">PRICE:</label>
                                    <input type="text" placeholder="Enter product price" name="price" className="form-control" value={price} onChange={(e) => setPrice(e.target.value)}/>
                                </div>
                                <div className="text-center">
                                    <button className="btn" onClick={(e) => saveOrUpdateProduct(e)}>
                                        <img
                                            src="https://www.freeiconspng.com/uploads/save-download-icon-10.png"
                                            alt="SAVE" />
                                    </button>
                                    <Link to="/products" className="btn">
                                        <img
                                            src="https://www.freeiconspng.com/thumbs/remove-icon-png/remove-icon-png-8.png"
                                            alt="CANCEL" />
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddProduct;