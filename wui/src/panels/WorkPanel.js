import React from "react";
import Context from "../Context";
import Ajax from "../Ajax";

let Ons = require('react-onsenui');

class WorkPanel
    extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            selectedItemId: -1
        }
    }

    componentDidMount() {
        Ajax.getWorkVideos(this.props.workId).then(resp => this.context.updateWorkVideos(this.props.workId, resp.data));
    }

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        className="backButton" style={{flex: 'none'}}>
                Back
            </Ons.Button>

            <Ons.List dataSource={this.context.workVideos[this.props.workId]}
                      style={{flex: 1, overflow: 'auto'}}
                      modifier="inset"
                      renderRow={(item) => <Ons.ListItem key={item.id}
                                                         onClick={() => {
                                                             this.context.updateActiveWork(item);
                                                             this.setState({
                                                                 selectedItemId: item.id
                                                             })
                                                         }}
                                                         className={item.id === this.state.selectedItemId ? 'active' : ''}
                                                         modifier="">
                          <Ons.Icon icon="fa-youtube" style={{marginRight: '1em'}} />
                          {item.title}
                      </Ons.ListItem>}
            />
        </div>
    }
}

WorkPanel.contextType = Context;
export default WorkPanel;