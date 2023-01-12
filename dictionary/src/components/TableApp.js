import React, {Component} from "react";
import {Table} from 'react-bootstrap';
import '../App.css';

class TableApp extends Component {
	constructor(props) {
		super(props)
	}

	renderData(data, index) {
		return (
			<tr key={index}>
				<td>{data[0].word}</td>
				<td>{data[0].phonetics[1].text}</td>
				<td>{data[0].meanings[0].definitions[0].definition}</td>
			</tr>
		)
	}

	render() {
		return (
			<Table responsive striped bordered hover size="sm">
				<thead>
					<tr>
						<th>Word</th>
						<th>Phonetic</th>
						<th>Definition</th>
					</tr>
				</thead>
				<tbody>
					{this.props.data.map(this.renderData)}
				</tbody>
			</Table>
		)
	}
}

export default TableApp;