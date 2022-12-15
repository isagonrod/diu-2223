import React from "react";

export default function ButtonCounterRed(props) {
	return (
		<div>
			<button class={props.color} onClick={props.incrementNum}>Increment</button>
			<button class={props.color} onClick={props.decrementNum}>Decrement</button>
			<button class={props.color} onClick={props.resetNum}>Reset</button>
		</div>
	);
}