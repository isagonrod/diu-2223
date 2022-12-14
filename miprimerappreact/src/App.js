import React, {Component} from "react";
import logo from './logo.svg';
import './App.css';
import HelloComponentFunction from "./components/HelloComponentFunction";
import InputComponentFunction from "./components/InputComponentFunction";
import HelloComponent from "./components/HelloComponent";
import InputComponent from "./components/InputComponent";

class App extends Component {
	constructor() {
		super(0);
		this.state = {
			name: 'Isa'
		}
	}

	changeName = (event) => {
		this.setState({
			name: event.target.value
		})
	}

	render() {
		return (
			<div className="App">
				<header className="App-header">
					<img src={logo} className="App-logo" alt="logo"/>
					<HelloComponent></HelloComponent>
					<InputComponent nombre={this.state.name} cambiarNombre={this.changeName}></InputComponent>
				</header>
			</div>
		)
	}
}

export default App;
