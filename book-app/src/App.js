
import React, { Component } from "react";
import logo from './logo.svg';
import './App.css';
import GraphViewer from "./components/GraphViewer";

class App extends Component {
  state = {
    visible: true
  };

  render() {
    return (
      <div className="App">
        <GraphViewer />
      </div>
    );
  }
}

export default App;