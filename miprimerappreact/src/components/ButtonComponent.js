import React, {Component} from "react";

export default class ButtonComponent extends Component {
	render() {
		return (
			<div>
				<button onClick={this.props.incrementNum}>Increment</button>
				<button onClick={this.props.decrementNum}>Decrement</button>
				<button onClick={this.props.resetNum}>Reset</button>
			</div>
		);
	}
}