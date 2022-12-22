import React, {Component} from "react";
import PropTypes from "prop-types";
import Buttons from "./Buttons";

export default class CalculatorButtons extends Component {
	static propTypes = {
		clickHandler: PropTypes.func,
	};

	handleClick = buttonName => {
		this.props.clickHandler(buttonName);
	};

	render() {
		return (
			<div className="calculatorButtons">
				<div>
					<Buttons name="AC" clickHandler={this.handleClick} />
					<Buttons name="+/-" clickHandler={this.handleClick} />
					<Buttons name="%" clickHandler={this.handleClick} />
					<Buttons name="÷" clickHandler={this.handleClick} orange />
				</div>
				<div>
					<Buttons name="7" clickHandler={this.handleClick} />
					<Buttons name="8" clickHandler={this.handleClick} />
					<Buttons name="9" clickHandler={this.handleClick} />
					<Buttons name="x" clickHandler={this.handleClick} orange />
				</div>
				<div>
					<Buttons name="4" clickHandler={this.handleClick} />
					<Buttons name="5" clickHandler={this.handleClick} />
					<Buttons name="6" clickHandler={this.handleClick} />
					<Buttons name="-" clickHandler={this.handleClick} orange />
				</div>
				<div>
					<Buttons name="1" clickHandler={this.handleClick} />
					<Buttons name="2" clickHandler={this.handleClick} />
					<Buttons name="3" clickHandler={this.handleClick} />
					<Buttons name="+" clickHandler={this.handleClick} orange />
				</div>
				<div>
					<Buttons name="0" clickHandler={this.handleClick} wide />
					<Buttons name="." clickHandler={this.handleClick} />
					<Buttons name="=" clickHandler={this.handleClick} orange />
				</div>
			</div>
		);
	}
}