import 'bootstrap/dist/css/bootstrap.min.css';

const Header = () => {

    return (
        <div>
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div>
                        <img src="src/images/products.png"/>
                        <a href="/products" className="navbar-brand">PRODUCTS LIST</a>
                    </div>
                </nav>
            </header>
        </div>
    )
}

export default Header;