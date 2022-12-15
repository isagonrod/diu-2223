import React, { useState } from 'react';
import './App.css';

function AppHook() {
	let [count, setCount] = useState(0);

	return (
		<div>
			<header className="App-header">
				<h1>{count}</h1>
				<button onClick={() => setCount(count + 1)}>Increment</button>
				<button onClick={() => setCount(count - 1)}>Decrement</button>
				<button onClick={() => setCount(count = 0)}>Reset</button>
			</header>
		</div>
	);
}

export default AppHook
