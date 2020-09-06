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
            <div style={{flex: 1, display: 'flex', alignContent: 'center', justifyContent: 'center'}}>
                <div>
                    <Ons.List dataSource={this.context.workVideos[this.props.workId]}
                              className="workVideoList"
                              modifier="inset"
                              style={{
                                  width: '50%',
                                  marginLeft: '25%',
                                  marginTop: '1em'
                              }}
                              renderRow={(item) => <Ons.ListItem key={item.id}
                                                                 onClick={() => {
                                                                     this.context.updateActiveWork(item);
                                                                     this.setState({
                                                                         selectedItemId: item.id
                                                                     })
                                                                 }}
                                                                 className={item.id === this.state.selectedItemId ? 'active' : ''}
                                                                 modifier="">
                                  <Ons.Icon icon="fa-youtube" style={{marginRight: '1em', color: '#e00'}}/>
                                  <div style={{width: 'calc(100% - 3em)'}}>
                                      <div style={{
                                          overflow: 'hidden',
                                          textOverflow: 'ellipsis',
                                          whiteSpace: 'nowrap',
                                          maxWidth: '100%'
                                      }}>{item.title}</div>
                                  </div>
                              </Ons.ListItem>}
                    />
                </div>
            </div>
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        className="backButton" style={{flex: 'none'}}>
                Back
            </Ons.Button>

        </div>
    }
}

WorkPanel.contextType = Context;
export default WorkPanel;