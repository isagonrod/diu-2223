import React, {Component} from "react";
import './css/App.css';
import calculate from "./logic/calculate";
import CalculatorDisplay from "./components/CalculatorDisplay";
import CalculatorButtons from "./components/CalculatorButtons";

export default class App extends Component {
    state = {
        total: null,
        next: null,
        operation: null
    };

    handleClick = buttonName => {
        this.setState(calculate(this.state, buttonName));
    }

    render() {
        return (
            <div className="component-app">
                <CalculatorDisplay value={this.state.next || this.state.total || "0"} />
                <CalculatorButtons clickHandler={this.handleClick} />
            </div>
        );
    }
}
