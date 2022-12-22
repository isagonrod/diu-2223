import React, {Component} from "react";
import './App.css';
import CalculatorButtons from "./components/CalculatorButtons";
import Display from "./components/Display";
import calculate from "./logic/calculate";

class App extends Component {
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
					<Display value={this.state.next || this.state.total || "0"}/>
					<CalculatorButtons clickHandler={this.handleClick} />
				</header>
			</div>
		);
	}
}

export default App;
