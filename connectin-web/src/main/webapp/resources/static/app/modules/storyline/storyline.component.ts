import {Component,OnInit} from "angular2/core";
import {StoryComponent} from './components/story/story.component';

@Component({
	selector:'storyline',
	templateUrl:'/resources/static/app/modules/storyline/storyline.component.html',
	directives:[StoryComponent]
	
})
export class StorylineComponent{
	
}