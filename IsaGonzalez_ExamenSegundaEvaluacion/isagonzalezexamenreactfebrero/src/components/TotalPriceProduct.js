import {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import ProductService from "../services/ProductService";

const TotalPriceProduct = () => {

    const [units, setUnits] = useState('');
    const [price] = useState(ProductService.getProductById.price);
    const [total, setTotal] = useState('0€');
    const {productId} = useParams();

    const calculateTotal = (e) => {
        e.currentTarget();

        if (productId && ProductService.getProductById(productId).active) {
            if (units <= ProductService.getProductById(productId).stock) {
                setTotal(units * price + '€');
                ProductService.updateProduct(productId).stock -= units;
            }
        }
    }

    useEffect((e) => {
        ProductService.getProductById(productId).then((response) => {
            setUnits(response.data.stock)
            setTotal(response.data.total)
        }).catch(error => {
            console.log(error);
        });
    }, []);

    const title = () => {
        if (productId) {
            return <h2 className="text-center">{ProductService.getProductById(productId).name}</h2>
        }
    }

    const incrementStock = () => {
        if (ProductService.getProductById(productId).stock < 10) {
            ProductService.updateProduct(productId, ProductService.getProductById(productId).stock += 10);
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
                                    <label className="form-label">UNITS:</label>
                                    <input
                                        type="text"
                                        placeholder="Enter units"
                                        name="stock"
                                        className="form-control"
                                        value={units}
                                        onChange={(e) => setUnits(e.target.value)}/>
                                    <label className="form-label">TOTAL:</label>
                                    <input
                                        type="text"
                                        placeholder="Total price"
                                        name="total"
                                        className="form-control"
                                        value={total}
                                        onChange={(e) => setTotal(e.target.value)}/>
                                </div>
                                <div className="text-center">
                                    <button className="btn btn-primary" onClick={(e) => calculateTotal(e)}>
                                        CALCULATE
                                    </button>
                                    <Link to="/products" className="btn btn-danger">
                                        CANCEL
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default TotalPriceProduct;