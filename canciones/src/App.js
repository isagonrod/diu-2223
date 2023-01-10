import React, {Component} from 'react';
import {Row, Col, Container} from 'react-bootstrap';

class App extends Component {
	constructor() {
		super();
		this.state = {
			data: [],
			artist: '',
			title: '',
			lyrics: ''
		}
	}

	passParams = (data) => {
		let dataNew = this.state.data;
		dataNew.push(data)
		this.setState({data: dataNew});
	}

	componentDidMount() {
		fetch('https://api.lyrics.ovh/v1/{artist}/{title}')
			.then((response) => {
				if (response.ok) {
					return response.json();
				} else {
					throw new Error(response.statusText);
				}
			})
			.then((data) => {
				this.setState({data: data})
			});
	}

	render() {
		return (
			<Container>
				<Col>
					<Row>
						<FormApp passParams={this.passParams}/>
					</Row>
					<Row>
						<TableApp data={this.state.data}/>
					</Row>
				</Col>
				<Col>
					<Row>

					</Row>
				</Col>
			</Container>
		);
	}
}
