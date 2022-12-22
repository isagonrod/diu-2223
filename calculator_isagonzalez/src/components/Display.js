import React, {Component} from "react";
import PropTypes from "prop-types";

export default class Display extends Component {
    static propTypes = {
        value: PropTypes.string,
    };

    render() {
        return (
            <div className="result">
                <div>{this.props.value}</div>
            </div>
        );
    }
}