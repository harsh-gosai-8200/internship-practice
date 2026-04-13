
import { useState } from "react";
import AnimalShow from "./AnimalShow";
import './App.css';

function getRendomeAnimal(){
  const animal = ['bird', 'cat', 'cow', 'dog', 'gator', 'horse'];

  return animal[Math.floor(Math.random()*animal.length)];
}

function App(){
const [animal, setAnimals] = useState([]);

  const handleEvent = () => {
    setAnimals([...animal, getRendomeAnimal()]);
  };

  const renderedAnimals = animal.map((animals, index) => {
    return <AnimalShow type = {animals} key = {index} />;
  })

  return (
  <div className="app">
    {/* inline event 
    <button 
      onClick={() => console.log("Button was clicked!!")}>
      Add Animal
    </button> */}
    <button onClick={handleEvent}>Add Animal</button>
    <div className="animal-list">{renderedAnimals}</div>
  </div>
  );
};

export default App;