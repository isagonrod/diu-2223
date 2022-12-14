import React, {Component} from "react";

export default class NumComponent extends Component {
	render() {
		return (
			<div>
				<h1>{this.props.number}</h1>
			</div>
		)
	}
}