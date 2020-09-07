import React from "react";
import Context from "../Context";
import Ajax from "../Ajax";

let Ons = require('react-onsenui');

class BrowseFormComposerWorksPanel
    extends React.Component {

    componentDidMount() {
        Ajax.getFormComposerWorks(this.props.formId, this.props.composerId)
            .then(resp => {
                this.context.updateFormComposerWorks(this.props.formId, this.props.composerId, resp.data);
            })
    }

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>
            <Ons.List dataSource={this.context.formComposerWorks[this.props.formId + '_' + this.props.composerId]}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                      onClick={() => {
                          this.props.navigator.pushPage({
                              id: 'work',
                              workId: item.id
                          })
                      }}>
                          {item.title}
                      </Ons.ListItem>}
            />
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        className="backButton" style={{flex: 'none'}}>
                Back
            </Ons.Button>

        </div>
    }
}

BrowseFormComposerWorksPanel.contextType = Context;
export default BrowseFormComposerWorksPanel;