import React from "react";

export default function HelloComponentFunction(props) {
	return (
		<div>
			<h1>Hola {props.nombre}</h1>
		</div>
	)
}