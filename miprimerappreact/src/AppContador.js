import React, {Component} from "react";
import NumComponent from "./components/NumComponent";
import './App.css';
import logo from './logo.svg';
import ButtonComponent from "./components/ButtonComponent";

class AppContador extends Component {
	constructor() {
		super(0);
		this.state = {
			num: '1'
		}
	}

	incrementNum = (event) => {
		this.setState({
			num: event.target.value++
		})
	}

	decrementNum = (event) => {
		this.setState({
			num: event.target.value--
		})
	}

	resetNum = (event) => {
		this.setState({
			num: event.target.value = 0
		})
	}

	render() {
		return (
			<div className="AppContador">
				<header className="App-header">
					<img src={logo} className="App-logo" alt="logo"/>
					<NumComponent number={this.state.num} />
					<ButtonComponent incrementNum={this.incrementNum} decrementNum={this.decrementNum} resetNum={this.resetNum} />
				</header>
			</div>
		)
	}
}

export default AppContador;