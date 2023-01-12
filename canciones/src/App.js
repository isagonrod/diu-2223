import React, {Component} from 'react';
import {Row, Col, Container} from 'react-bootstrap';
import FormApp from "./components/FormApp";
import TableApp from "./components/TableApp";

class App extends Component {
	constructor() {
		super()
		this.state = {
			word: '',
			data: []
		}
	}

	passParams = (data) => {
		let dataNew = this.state.data;
		dataNew.push(data)
		this.setState({data: dataNew});
	}

	componentDidMount() {
	// 	fetch('https://api.dictionaryapi.dev/api/v2/entries/en/')
	// 		.then((response) => {
	// 			if (response.ok) {
	// 				return response.json();
	// 			} else {
	// 				throw new Error(response.statusText);
	// 			}
	// 		})
	// 		.then((data) => {
	// 			this.setState({data: data})
	// 		});
	}

	render() {
		return (
			<Container>
				<Row>
					<Col>
						<FormApp passParams={this.passParams}/>
					</Col>
				</Row>
				<Row>
					<Col>
						<TableApp data={this.state.data}/>
					</Col>
				</Row>
			</Container>
		);
	}
}

export default App;