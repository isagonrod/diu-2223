import React, { Component } from 'react';
import TutorialDataService from "../services/tutorial.service"
import { Link } from 'react-router-dom';

export default class AddTutorial extends Component {
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.setActiveTutorial = this.setActiveTutorial.bind(this);

        this.state = {
            tutorialID: null,
            tutorialTitle: null,
            tutorialDescription: null,
            tutorialPublished: false
        };
    }

    componentDidMount() {
        this.retrieveTutorials();
    }

    saveTutorial(id, title, description, published) {
        this.setState({
            tutorialID: id,
            tutorialTitle: title,
            tutorialDescription: description,
            tutorialPublished: published
        });
    }

    onChangeId(e) {
        const id = e.target.value;

        this.setState({ id: id });
    }

    onChangeTitle(e) {
        const title = e.target.value;

        this.setState({ title: title });
    }

    onChangeDescription(e) {
        const description = e.target.value;

        this.setState({ description: description });
    }

    render() {
        const { tutorialID, tutorialTitle, tutorialDescription, tutorialPublished} = this.state;

        return (
            <div className="edit-form">
                <h4>Tutorial</h4>
                <form>
                    <div className="form-group">
                        <label htmlFor="id">ID</label>
                        <input
                            type="text"
                            className="form-control"
                            id="id"
                            value={tutorialID}
                            onChange={this.onChangeId}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="title">TITLE</label>
                        <input
                            type="text"
                            className="form-control"
                            id="title"
                            value={tutorialTitle}
                            onChange={this.onChangeTitle}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">DESCRIPTION</label>
                        <input
                            type="text"
                            className="form-control"
                            id="description"
                            value={tutorialDescription}
                            onChange={this.onChangeDescription}/>
                    </div>
                    <div className="form-group">
                        <label>STATUS</label>
                        { tutorialPublished ? "Published" : "Pending" }
                    </div>
                </form>
                { tutorialPublished ? (
                    <button className="badge badge-primary mr-2" onClick={() => this.updatePublished(false)}>
                        UNPUBLISH
                    </button>
                ) : (
                    <button className="badge badge-primary mr-2" onClick={() => this.updatePublished(true)}>
                        PUBLISH
                    </button>
                )}
                <button className="badge badge-info mr-2" onClick={this.saveTutorial}>
                    SAVE
                </button>
            </div>
        );
    }


}