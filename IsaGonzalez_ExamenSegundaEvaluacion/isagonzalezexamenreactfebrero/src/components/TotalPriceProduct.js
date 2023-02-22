import {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import ProductService from "../services/ProductService";

const TotalPriceProduct = () => {

    const [id] = useState('');
    const [name] = useState('');
    const [brand] = useState('');
    let [stock, setStock] = useState('');
    const [price] = useState('');
    const [active] = useState('');

    const [units, setUnits] = useState('');
    const [total, setTotal] = useState('0€');
    const {productId} = useParams();

    const product = {id, name, brand, stock, price, active};

    const calculateTotal = (e) => {
        e.currentTarget();

        if (productId === id && active) {
            if (units <= stock) {
                setTotal(parseInt(units) * parseFloat(price) + '€');
                ProductService.updateProduct(productId, product).stock -= units;
            }
        }
    }

    useEffect(() => {
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
            setStock(stock -= units);
            ProductService.updateProduct(productId, product);
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
                                    <h4>Unidades de Stock: {stock}</h4>
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
                                    </button>{' '}
                                    <Link to="/products" className="btn btn-danger">
                                        CANCEL
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div className="text-center">
                        {stock < 10 ?
                            <h3 color="red">"Unidades bajas"</h3>
                            :
                            <h3 color="green">"Unidades OK"</h3>}
                        <button className="btn btn-success" onClick={incrementStock}>OK</button>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default TotalPriceProduct;