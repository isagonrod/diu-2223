import logo from './logo.svg';
import './App.css';
import Display from "./components/Display";
import CalculatorButtons from "./components/CalculatorButtons";
import calculate from "./logic/calculate";

export default class App {
    state = {
        total: null,
        operation: null
    };

    handleClick = buttonName => {
        this.setState(calculate(this.state, buttonName));
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <Display value={this.state.next || this.state.total || "0"}/>
                    <CalculatorButtons clickHandler={this.handleClick}/>
                </header>
            </div>
        );
    }

    set setState(value) {
        this.state = value;
    }
}
