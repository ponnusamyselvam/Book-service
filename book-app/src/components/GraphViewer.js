import React from "react";
import Graph from "react-graph-vis";

export default class GraphViewer extends React.Component {
    state = {
        loading: true,
        grpaphdata: null,
        opt: null,
        eve: null
      };
    
      async componentDidMount() {
        const options = {
            edges: {
              color: "#000000",
              arrows: {
                to: {
                  enabled: false,
                  scaleFactor: 0.5
                }
              }
            }
          };
    
          const events = {
            select: function(event) {
              var { nodes, edges } = event;
              console.log("Selected nodes:");
              console.log(nodes);
              console.log("Selected edges:");
              console.log(edges);
            }
          };
        const url = "http://localhost:8080/graph";
        const response = await fetch(url);
        const data = await response.json();
        console.log(data);
        const grp = JSON.parse(data.graph);
        this.setState({ grpaphdata: grp, loading: false, opt:options, eve:events });
        console.log(this.state)
      }
    
      render() {
        if (this.state.loading) {
          return <div>loading...</div>;
        }
    
        if (!this.state.grpaphdata) {
          return <div>didn't get a graph</div>;
        }
    
        return (
            <Graph graph={this.state.grpaphdata} options={this.state.opt} events={this.state.eve} style={{ height: "700px" }} />
        );
      }
}