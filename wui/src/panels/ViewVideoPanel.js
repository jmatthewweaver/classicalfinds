import React from "react";
import Context from "../Context";

class ViewVideoPanel
    extends React.Component {

    render() {
        if(!this.context.activeWork) {
            return null;
        }

        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap',
            alignItems: 'center',
            justifyContent: 'center'
        }}>
            <div style={{flex: 1, color: '#fff'}}>
                <h1 style={{paddingLeft: '1em', paddingRight: '1em', fontSize: '24px'}}>{this.context.activeWork.title}</h1>
                <p style={{paddingLeft: '1em', paddingRight: '1em'}}>{this.context.activeWork.description}</p>
            </div>
            <div style={{flex: 1, padding: '1em'}}>
            <iframe src={'http://www.youtube.com/embed/' + this.context.activeWork.videoId + "?autoplay=1"}
                    style={{height: '100%', width: 'auto'}}/>
            </div>
        </div>
    }
}

ViewVideoPanel.contextType = Context;
export default ViewVideoPanel;