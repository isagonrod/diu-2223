import React, {Component} from "react";
import {Form, Button, Row, Col, FormLabel, FormControl, FormGroup} from 'react-bootstrap';
import '../App.css';
import button from "bootstrap/js/src/button";

class FormApp extends Component {
    constructor(props) {
        super(props)
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
                <FormGroup className="form">
                    <Row>
                        <Col>
                            <FormLabel>WORD: </FormLabel>
                            <FormControl required placeholder="Enter an english word" name="word"
                                         value={this.state.word} onChange={this.handleChange} />
                        </Col>
                        <Col>
                            <Button type="submit" className="button">SEARCH</Button>
                        </Col>
                    </Row>
                </FormGroup>
            </Form>
        )
    }
}

export default FormApp;