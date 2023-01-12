import React, {Component} from "react";
import {Form, Button, Row, Col, FormGroup} from 'react-bootstrap';

class FormApp extends Component {
	constructor() {
		super()
		this.state = {
			word: ''
		}
	}

	handleChange = event => {
		const name = event.target.name;
		const value = event.target.value;
		this.setState({[name]: value});
	}

	handleSubmit = event => {
		event.preventDefault();
		let url = 'https://api.dictionaryapi.dev/api/v2/entries/en/' + this.state.word;
		fetch(url, {
			// method: 'GET',
			// body: JSON.stringify({
			// 	word: this.state.word
			// }),
			headers: {
				"Content-type": "application/json; charset=UTF-8"
			}
		})
			.then((response) => {
				if (response.ok) {
					return response.json();
				} else {
					throw new Error(response.statusText);
				}
			})
			.then(data => {
				this.props.passParams(data);
			})
	}

	render() {
		return (
			<Form onSubmit={this.handleSubmit}>
				<Row>
					<Col>
						<FormGroup>
							<Form.Label>WORD</Form.Label>
							<Form.Control required placeholder="Enter an english word" name="word"
										  value={this.state.word} onChange={this.handleChange} />
						</FormGroup>
					</Col>
				</Row>
				<Row>
					<Col>
						<FormGroup>
							<Button type="submit">SEARCH</Button>
						</FormGroup>
					</Col>
				</Row>
			</Form>
		)
	}
}

export default FormApp;