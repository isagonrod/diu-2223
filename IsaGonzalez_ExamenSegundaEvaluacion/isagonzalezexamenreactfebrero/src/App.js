import './App.css';
import Header from "./components/Header";
import Footer from "./components/Footer";
import ListProducts from "./components/ListProducts";
import AddProduct from "./components/AddProduct";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
    return (
        <div>
            <Header/>
                <BrowserRouter>
                    <Routes>
                        <Route exact path='/' element={<ListProducts/>}/>
                        <Route path='/products' element={<ListProducts/>}/>
                        <Route path='/add-product' element={<AddProduct/>}/>
                        <Route path='/edit-product/:id' element={<AddProduct/>}/>
                    </Routes>
                </BrowserRouter>
            <Footer/>
        </div>
    );
}

export default App;