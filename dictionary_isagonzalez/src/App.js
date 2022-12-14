import React, {Component} from 'react';
import {Row, Col, Container} from 'react-bootstrap';
import FormApp from "./components/FormApp";
import TableApp from "./components/TableApp";
import "bootstrap/dist/css/bootstrap.min.css"
import './App.css';

class App extends Component {
	constructor(props) {
		super(props)
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
						<TableApp className="table" data={this.state.data}/>
					</Col>
				</Row>
			</Container>
		);
	}
}

export default App;