import React, { useState } from 'react'

type Props = {firstName:string}

export default function MyComponent(props: Props) {
  return (
    <div>Hello {props.firstName}</div>
  )
}

export const ExampleCounter = () => {
  const [count, setCount] = useState<number>(0);

  const increaseCounter = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <p>Counter was clicked {count} times.</p>
      <button onClick={increaseCounter}>
        Click me
      </button>
    </div>
  );
};