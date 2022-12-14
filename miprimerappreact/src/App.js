import React, {Component} from "react";
import logo from './logo.svg';
import './App.css';
import HelloComponent from "./components/HelloComponent";
import InputComponent from "./components/InputComponent";

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <HelloComponent></HelloComponent>
//       </header>
//     </div>
//   );
// }

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
					<HelloComponent nombre={this.state.name}/>
					<InputComponent nombre={this.state.name} cambiarNombre={this.changeName} />
				</header>
			</div>
		)
	}
}

export default App;
