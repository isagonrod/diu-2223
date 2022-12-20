import React, { useState } from "react";
import './App.css';
import CalculatorButtons from "./components/CalculatorButtons";

function App() {
	const [result, setResult] = useState(0);

	return (
		<div className="App">
			<header className="App-header">
				<output className="result">{result}</output>
				<CalculatorButtons/>
			</header>
		</div>
	);
}

export default App;
