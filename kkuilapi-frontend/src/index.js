import ReactDOM from 'react-dom/client';

import App from './App';
import * as serviceWorker from './serviceWorker';
import reportWebVitals from './reportWebVitals';
import './assets/styles/index.css';

// ----------------------------------------------------------------------

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(<App />);

serviceWorker.unregister();

reportWebVitals();
