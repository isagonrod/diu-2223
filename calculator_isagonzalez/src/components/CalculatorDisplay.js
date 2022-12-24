import React, {Component} from "react";
import PropTypes from "prop-types";

import "../css/CalculatorDisplay.css";

export default class CalculatorDisplay extends Component {
    static propTypes = {
        value: PropTypes.string,
    };

    render() {
        return (
            <div className="component-calculator-display">
                <div>{this.props.value}</div>
            </div>
        );
    }
}