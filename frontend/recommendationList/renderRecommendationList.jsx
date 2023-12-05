import React from "react";
import { createRoot } from "react-dom/client";
import RecommendaionsList from "./RecommendaionsList";

const root = createRoot(document.getElementById("recommendationListRoot"));
root.render(<RecommendaionsList />);
