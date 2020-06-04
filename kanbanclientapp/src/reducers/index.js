import {combineReducers} from 'redux';
import errorReducer from './errorReducer';
import projectReducer from './projectReducer';
import backlogReducer from './backlogReducer';
import securityReducers from './securityReducers';

export default combineReducers({
  errors: errorReducer,
  project: projectReducer,
  backlog: backlogReducer,
  security: securityReducers,
});
