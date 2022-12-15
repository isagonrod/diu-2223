import React, { useState } from 'react';
import './App.css';
import ButtonCounterRed from "./components/ButtonCounterRed";

function AppHook() {
	const [count, setCount] = useState(0);
	const colorR = 'buttonsRed';

	const incrementNum = () => setCount(count + 1);
	const decrementNum = () => setCount(count - 1);
	const resetNum = () => setCount(0);

	return (
		<div>
			<header className="App-header">
				<h1>{count}</h1>
				<ButtonCounterRed
					color={colorR}
					incrementNum={incrementNum}
					decrementNum={decrementNum}
					resetNum={resetNum} />

				{/*<button className="buttonsRed" onClick={() => setCount(count + 1)}>Increment</button>*/}
				{/*<button onClick={() => setCount(count - 1)}>Decrement</button>*/}
				{/*<button onClick={() => setCount(count = 0)}>Reset</button>*/}
			</header>
		</div>
	);
}

export default AppHook;
