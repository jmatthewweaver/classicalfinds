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
            flexFlow: 'row nowrap',
            alignItems: 'center',
            justifyContent: 'center'
        }}>
            <iframe src={'http://www.youtube.com/embed/' + this.context.activeWork.videoId + "?autoplay=1"}/>
        </div>
    }
}

ViewVideoPanel.contextType = Context;
export default ViewVideoPanel;