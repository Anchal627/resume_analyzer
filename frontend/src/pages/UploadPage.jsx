import { useState } from "react";
import axios from "axios";

import { useNavigate } from "react-router-dom";
function UploadPage() {
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();
  const handleUpload = async () => {
    if (!file) {
      return alert("Upload a file first");
    }
    const formData = new FormData();
    formData.append("file", file);
    try {
      setLoading(true);
      const response = await axios.post(
        "http://localhost:8080/api/resume/upload",
        formData,
      );

      console.log(response.data);
      navigate("/dashboard", { state: response.data });
      alert("Analysis Complete!");
    } catch (err) {
      console.error(err);
      alert("Error");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="h-screen bg-black flex items-center justify-center">
      <div className="bg-gray-600 p-10 rounded-2xl shadow-2xl text-center w-[400px]">
        <h1 className="text-3xl font-bold mb-6 text-white underline">
          AI Resume Analyzer
        </h1>
        <input
          type="file"
          className="mb-4 w-full border p-10 rounded"
          onChange={(e) => setFile(e.target.files[0])}
        />
        <button
          className=" w-full py-2 bg-indigo-500 rounded-lg hover:bg-blue-400 transition duration-300 text-black"
          onClick={handleUpload}
        >
          {loading ? "Analyzing...." : "Upload And Analyze"}
        </button>
      </div>
    </div>
  );
}

export default UploadPage;
