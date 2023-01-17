import React, {Component} from "react";
import {Form, Button, Row, Col, FormLabel, FormControl, FormGroup} from 'react-bootstrap';
import '../App.css';

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
        document.querySelector('#button').disabled = true;
        document.querySelector('#button').innerHTML = 'SEARCHING...';

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
                    document.querySelector('#button').disabled = false;
                    document.querySelector('#button').innerHTML = 'SEARCH';
                    throw new Error(response.statusText);
                }
            })
            .then(data => {
                this.props.passParams(data);
                document.querySelector('#button').disabled = false;
                document.querySelector('#button').innerHTML = 'SEARCH';
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
                            <Button id="button" type="submit" className="button">SEARCH</Button>
                        </Col>
                    </Row>
                </FormGroup>
            </Form>
        )
    }
}

export default FormApp;