import { useLocation, useNavigate } from "react-router-dom";
import "react-circular-progressbar/dist/styles.css";
import { CircularProgressbar } from "react-circular-progressbar";

function Dashboard() {
  const location = useLocation();
  const result = location.state;
  const navigate = useNavigate();

  if (!result) {
    return (
      <div className="h-screen flex items-center justify-center">
        <button
          onClick={() => navigate("/")}
          className="bg-indigo-500 text-white px-4 py-2 rounded"
        >
          Go Back
        </button>
      </div>
    );
  }

  // 🔥 Split suggestions into lines
  const suggestions = result.suggestions
    .split("\n")
    .filter((l) => l.trim() !== "");

  return (
    <div className="min-h-screen bg-gradient-to-br from-black via-gray-900 to-black p-6 flex items-center justify-center">
      <div className="bg-white p-8 rounded-2xl shadow-2xl w-full max-w-5xl max-h-[95vh] overflow-y-auto">
        {/* TITLE */}
        <h1 className="text-3xl font-bold mb-6 text-center text-gray-800">
          Resume Analysis
        </h1>

        {/* SCORE CARD */}
        <div className="flex justify-center mb-8">
          <div className="w-40 h-40">
            <CircularProgressbar
              value={result.score}
              text={`${result.score}%`}
            />
          </div>
        </div>
        <div className="grid md:grid-cols-3 gap-4 mb-8">
          {/* EMAIL */}
          <div className="bg-indigo-50 p-4 rounded-xl shadow">
            <p className="text-gray-500 text-sm">Email</p>
            <h2 className="font-bold text-lg break-words">{result.email}</h2>
          </div>

          {/* SKILLS */}
          <div className="bg-green-50 p-4 rounded-xl shadow">
            <p className="text-gray-500 text-sm">Skills Found</p>
            <h2 className="font-bold text-2xl">{result.skills.length}</h2>
          </div>

          {/* SUGGESTIONS */}
          <div className="bg-purple-50 p-4 rounded-xl shadow">
            <p className="text-gray-500 text-sm">AI Suggestions</p>
            <h2 className="font-bold text-2xl">{suggestions.length - 1}</h2>
          </div>
        </div>

        {/* EMAIL */}
        <div className="mb-6">
          <p className="font-semibold text-gray-700">Email:</p>
          <p className="text-gray-600">{result.email}</p>
        </div>

        {/* SKILLS */}
        <div className="mb-8">
          <p className="font-semibold mb-3 text-gray-700">Skills:</p>
          <div className="flex flex-wrap gap-3">
            {result.skills.map((skill) => (
              <span
                key={skill}
                className="bg-gradient-to-r from-indigo-500 to-purple-500 text-white px-4 py-2 rounded-full text-sm font-medium shadow hover:scale-110 transition duration-300"
              >
                {skill}
              </span>
            ))}
          </div>
        </div>

        {/* AI SUGGESTIONS */}
        <div>
          <p className="font-semibold mb-3 text-gray-700">🤖 AI Suggestions:</p>

          <div className="grid md:grid-cols-2 gap-4">
            {suggestions
              .filter(
                (line) =>
                  line.trim() !== "" &&
                  !line.toLowerCase().includes("here are") &&
                  !line.toLowerCase().includes("bullet points"),
              )
              .map((line, index) => (
                <div
                  key={index}
                  className="bg-gray-50 border-l-4 border-indigo-500 p-4 rounded-lg shadow hover:shadow-md transition"
                >
                  {line.replace(/\*\*/g, "").replace(/\*/g, "")}
                </div>
              ))}
          </div>
        </div>

        {/* BACK BUTTON */}

        <button
          onClick={() => navigate("/")}
          className="mt-8 bg-gray-800 text-white px-5 py-2 rounded-lg hover:bg-gray-600 transition"
        >
          Analyze Another Resume
        </button>
      </div>
    </div>
  );
}

export default Dashboard;
