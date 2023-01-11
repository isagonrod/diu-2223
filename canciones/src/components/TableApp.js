import React, {Component} from "react";
import {Table} from 'react-bootstrap';

class TableApp extends Component {
	constructor() {
		super()
	}

	renderData(data, index) {
		return (
			<tr key={index}>
				<td>{data.word}</td>
				<td>{data['phonetics'][1].text}</td>
				<td>{data['meanings'][1]['definitions'][1].definition}</td>
			</tr>
		)
	}

	render() {
		return (
			<Table responsive striped border hover size="sm">
				<thead>
					<tr>
						<th>WORD</th>
						<th>PHONETIC</th>
						<th>DEFINITION</th>
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