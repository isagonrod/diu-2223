import {Component} from "react";
import PropTypes from "prop-types";

export default class Buttons extends Component {
    static propTypes = {
        name: PropTypes.string,
        orange: PropTypes.bool,
        wide: PropTypes.bool,
        clickHandler: PropTypes.func,
    };

    buttonClick = () => {
        this.props.clickHandler(this.props.name);
    };

    render() {
        const className = [
            "buttons",
            this.props.orange ? "operation" : "",
            this.props.wide ? "doubleWidth" : "",
        ];

        return (
            <div className={className.join(" ").trim()}>
                <button onClick={this.handleClick}>{this.props.name}</button>
            </div>
        )
    }
}