import 'bootstrap/dist/css/bootstrap.min.css';

const Header = () => {

    return (
        <div>
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div>
                        <img
                            src="https://cdn3d.iconscout.com/3d/premium/thumb/product-5806313-4863042.png"
                            alt="PRODUCTS"/>
                        <a href="/products" className="navbar-brand">PRODUCTS LIST</a>
                    </div>
                </nav>
            </header>
        </div>
    )
}

export default Header;