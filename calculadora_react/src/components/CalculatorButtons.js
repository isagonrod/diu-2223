import React from "react";
import calculate from "../logic/calculate";
import isNumber from "../logic/isNumber";

export default function CalculatorButtons(props) {
	return (
		<div className="container">
			<div>
				<button onClick={calculate(props, this)} name="AC">AC</button>
				<button onClick={calculate(props, this)} name="+/-">+/-</button>
				<button onClick={calculate(props, this)} name="%">%</button>
				<button className="operation" onClick={calculate(props, this)} name="÷">÷</button>
			</div>
			<div>
				<button onClick={isNumber()} name="7">7</button>
				<button onClick={isNumber()} name="8">8</button>
				<button onClick={isNumber()} name="9">9</button>
				<button className="operation" onClick={calculate(props, this)} name="x">x</button>
			</div>
			<div>
				<button onClick={isNumber()} name="4">4</button>
				<button onClick={isNumber()} name="5">5</button>
				<button onClick={isNumber()} name="6">6</button>
				<button className="operation" onClick={calculate(props, this)} name="-">-</button>
			</div>
			<div>
				<button onClick={isNumber()} name="1">1</button>
				<button onClick={isNumber()} name="2">2</button>
				<button onClick={isNumber()} name="3">3</button>
				<button className="operation" onClick={calculate(props, this)} name="+">+</button>
			</div>
			<div>
				<button className="dobleAncho" onClick={isNumber()} name="0">0</button>
				<button onClick={calculate(props, this)} name="punto">.</button>
				<button className="operation" onClick={calculate(props, this)} name="=">=</button>
			</div>
		</div>

	)
}