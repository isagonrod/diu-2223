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

    onChangeTitle(e) {
        const title = e.target.value;

        this.setState({
            title: title
        });
    }

    saveTutorial(id, title, description, published) {
        this.setState({
            tutorialID: id,
            tutorialTitle: title,
            tutorialDescription: description,
            tutorialPublished: published
        });
    }

    render() {
        const { tutorialID, tutorialTitle, tutorialDescription, tutorialPublished} = this.state;

        return (
            <div>
                <div>
                    <label>
                        <strong>ID:</strong>
                    </label>{" "}
                    {tutorialID.id}
                </div>
                <div>
                    <label>
                        <strong>Title:</strong>
                    </label>{" "}
                    {tutorialTitle.title}
                </div>
                <div>
                    <label>
                        <strong>Description:</strong>
                    </label>{" "}
                    {tutorialDescription.description}
                </div>
                <div>
                    <label>
                        <strong>Published:</strong>
                    </label>{" "}
                    {tutorialPublished.published ? "Published" : "Pending"}
                </div>

                <button
                    className="btn btn-outline-primary"
                    type="button"
                    onClick={this.saveTutorial}>
                    SAVE
                </button>

            </div>
        )
    }


}