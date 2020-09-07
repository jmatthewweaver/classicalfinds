import React from "react";
import BrowseGenresPanel from "./BrowseGenresPanel";
import BrowseHomePanel from "./BrowseHomePanel";
import BrowseGenreComposersPanel from "./BrowseGenreComposersPanel";
import BrowseGenreComposerWorksPanel from "./BrowseGenreComposerWorksPanel";
import WorkPanel from "./WorkPanel";
import BrowseErasPanel from "./BrowseErasPanel";
import BrowseEraComposersPanel from "./BrowseEraComposersPanel";
import BrowseComposersPanel from "./BrowseComposersPanel";
import BrowseComposerWorksPanel from "./BrowseComposerWorksPanel";
import BrowseFormsPanel from "./BrowseFormsPanel";
import BrowseFormComposersPanel from "./BrowseFormComposersPanel";
import BrowseFormComposerWorksPanel from "./BrowseFormComposerWorksPanel";

let Ons = require('react-onsenui');

class BrowsePanel
    extends React.Component {

    render() {
        return <Ons.Page>
            <Ons.Navigator renderPage={(route, navigator) => {
                switch(route.id) {
                    case 'home':
                        return <Ons.Page key="browseHome">
                            <BrowseHomePanel navigator={navigator} />
                        </Ons.Page>

                    case 'genres':
                        return <Ons.Page key="browseGenres">
                            <BrowseGenresPanel navigator={navigator} />
                        </Ons.Page>

                    case 'genreComposers':
                        return <Ons.Page key="browseGenreComposers">
                            <BrowseGenreComposersPanel genreId={route.genreId} navigator={navigator} />
                        </Ons.Page>

                    case 'genreComposerWorks':
                        return <Ons.Page key="browseGenreComposerWorks">
                            <BrowseGenreComposerWorksPanel genreId={route.genreId} composerId={route.composerId} navigator={navigator} />
                        </Ons.Page>

                    case 'eras':
                        return <Ons.Page key="browseEras">
                            <BrowseErasPanel navigator={navigator} />
                        </Ons.Page>

                    case 'eraComposers':
                        return <Ons.Page key="browseEraComposers">
                            <BrowseEraComposersPanel eraId={route.eraId} navigator={navigator} />
                        </Ons.Page>

                    case 'composers':
                        return <Ons.Page key="browseComposers">
                            <BrowseComposersPanel navigator={navigator} />
                        </Ons.Page>

                    case 'composerWorks':
                        return <Ons.Page key="browseComposerWorks">
                            <BrowseComposerWorksPanel composerId={route.composerId} navigator={navigator} />
                        </Ons.Page>

                    case 'work':
                        return <Ons.Page key="browseWork">
                            <WorkPanel workId={route.workId} navigator={navigator} />
                        </Ons.Page>

                    case 'forms':
                        return <Ons.Page key="browseForms">
                            <BrowseFormsPanel navigator={navigator} />
                        </Ons.Page>

                    case 'formComposers':
                        return <Ons.Page key="formComposers">
                            <BrowseFormComposersPanel formId={route.formId} navigator={navigator} />
                        </Ons.Page>

                    case 'formComposerWorks':
                        return <Ons.Page key="formComposerWorks">
                            <BrowseFormComposerWorksPanel formId={route.formId} composerId={route.composerId} navigator={navigator} />
                        </Ons.Page>

                    default:
                        return undefined;
                }
            }}
            initialRoute={{id: 'home'}} />
        </Ons.Page>
    }
}

export default BrowsePanel;