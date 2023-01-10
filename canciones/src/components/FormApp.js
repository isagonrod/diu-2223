import React, {Component} from "react";
import {Form, Button, Row, Col, FormGroup} from 'react-bootstrap';

class FormApp extends Component {
	constructor() {
		super();
		this.state = {
			artist: '',
			title: ''
		}
	}

	handleChange = event => {
		const name = event.target.name;
		const value = event.target.value;
		this.setState({[name]: value});
	}

	handleSubmit = event => {
		event.preventDefault();
	}
}